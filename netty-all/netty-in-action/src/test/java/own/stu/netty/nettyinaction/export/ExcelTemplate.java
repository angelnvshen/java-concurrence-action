package own.stu.netty.nettyinaction.export;

import commonj.sdo.DataObject;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

/**
 * Excel模板实现类<BR>
 * 实现通过自定义Excel数据模版,将结果集填充到模版相应位置，自动创建输出到指定的文件，允许Excel模版设置公式，调用方法如下：<BR>
 * <pre>
 *     ExcelTemplate template=new ExcelTemplate(templateFilePath,outputFilePath)
 *     //template.setIncludeFormula(true);设置包含公式
 *     template.generate(ResultSet);//resultset为ArrayList对象,数据行以Map封装
 *     //template.generate(titleMap,dataList)//显示主表、明细表信息
 * </pre>
 *
 * @author zhangle
 */


public class ExcelTemplate {

    /**
     * 模板文件名
     */
    private String templateFile;

    /**
     * 输出文件名
     */
    private String outputFile;

    /**
     * Excel模板定义的输出字段名数组
     */
    private String[] fieldNames;

    /**
     * 输出的起始行,默认为-1,不输出
     */
    private int startRow = -1;

    private int tempStartRowNum = -1;

    /**
     * 默认字体大小
     */
    private int fontSize = 10;
    /**
     * 默认字体
     */
    private String fontName = "宋体";

    /**
     * 是否设置信息标题栏边框,默认情况不设置边框
     */
    private boolean titleCellBold = false;

    /**
     * 是否设置空白栏边框，默认情况不设置边框
     */
    private boolean blankCellBold = false;
    /**
     * 是否自动分工作薄
     */
    private boolean autoSheet = false;
    /**
     * 是否自动分页
     */
    private boolean autoPagination = false;
    /**
     * 分页行数
     */
    private int maxrow = -1;
    /**
     * 是否有公式
     */
    private boolean hasFormula = false;

    /**
     * 关键字
     * &-表示模版信息内容字段
     * #-表示模版明细内容字段
     * formula-表示模版函数关键字
     * ~-表示Cell当前行，当包含":"时，表示当前行减1
     */
    private final String TITLE_FLAG = "&";
    private final String CONTENT_FLAG = "#";
    private final String FORMULA_FLAG = "formula";
    private final String UNLIMIT_FLAG = "~";
    private final String FIELD_AUTO_ID = "_id";

    /**
     * 公式计算操作符号
     */
    private final String[] OP_FLAG = new String[]{"+", "-", "*", "/", "%", ":"};

    private String version = "";

    /**
     * 默认构造函数
     */
    public ExcelTemplate() {

    }

    /**
     * 构造器
     *
     * @param templateFile 模版文件
     * @param outputFile   输出文件
     */
    public ExcelTemplate(String templateFile, String outputFile) {
        this.templateFile = templateFile;
        this.outputFile = outputFile;
    }

    /**
     * 设置模版文件是否包含Excel公式
     *
     * @param hasFormula
     */
    public void setIncludeFormula(boolean hasFormula) {
        this.hasFormula = hasFormula;
    }

    /**
     * 设置标题栏是否需要边框
     *
     * @param titleCellBold
     */
    public void setTitleCellBold(boolean titleCellBold) {
        this.titleCellBold = titleCellBold;
    }

    /**
     * 设置空白行是否需要显示边框
     *
     * @param blankCellBold
     */
    public void setBlankCellBold(boolean blankCellBold) {
        this.blankCellBold = blankCellBold;
    }

    /**
     * 设置是否分工作薄
     *
     * @param autoSheet
     */
    public void setAutoSheet(boolean autoSheet) {
        this.autoSheet = autoSheet;
        this.autoPagination = (autoSheet ? false : autoPagination);
    }

    /**
     * 是否自动分页
     *
     * @param autoPagination
     */
    public void setAutoPagination(boolean autoPagination) {
        this.autoPagination = autoPagination;
        this.autoSheet = (autoPagination ? false : autoSheet);
    }

    /**
     * 设置分页最大行
     *
     * @param maxrow
     */
    public void setMaxRow(int maxrow) {
        this.maxrow = maxrow;
    }

    /**
     * 设置字体大小，默认10号字体
     *
     * @param size
     */
    public void setFontSize(int size) {
        this.fontSize = size;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    /**
     * 初始化工作模版，获取模版配置起始行(start)以及对应字段填充位置(fieldNames)
     *
     * @param sheet
     */
    private void initialize(Sheet sheet) {
        boolean setStart = false;
        int rows = sheet.getPhysicalNumberOfRows();

        for (int r = 0; r < rows; r++) {
            Row row = sheet.getRow(r);

            if (row != null) {
                int cells = row.getPhysicalNumberOfCells();
                for (short c = 0; c < cells; c++) {
                    Cell cell = row.getCell(c);
                    if (cell != null) {
                        String value = null;
                        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                            value = "" + cell.getNumericCellValue();
                        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                            value = "" + cell.getBooleanCellValue();
                        } else {
                            value = cell.getRichStringCellValue().getString();
                        }
                        if (value != null && !"".equals(value)) {
                            value = value.trim();
                            //内容数据
                            if (value.startsWith(CONTENT_FLAG)) {
                                if (!setStart) {
                                    this.startRow = r;//设置内容填充起始行
                                    this.fieldNames = new String[cells];
                                    setStart = true;
                                }
                                this.fieldNames[c] = value.substring(1);//初始化内容字段
                            }

                        }

                    }

                }
            }
        }

    }


    /**
     * 设置公式文本框为空白栏，当统计开始行减1==startRowNum时
     *
     * @param cell
     * @param startRowNum
     */
    private void setFormulaBlankCell(Cell cell, int startRowNum) {
        if (cell != null) {
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                String value = cell.getRichStringCellValue().getString();
                if (value != null) {
                    value = value.trim().toLowerCase();
                    if (value.startsWith(FORMULA_FLAG)) {
                        int index = value.indexOf("=");
                        String formula = value.substring(index + 1);
                        String flag = formula.substring(0, 1);
                        if (flag.equals(CONTENT_FLAG)) formula = formula.substring(1);
                        if (formula.indexOf(":") != -1) {
                            int rightIndex = formula.indexOf(")");
                            int leftIndex = formula.indexOf("(");
                            String content = formula.substring(leftIndex + 1, rightIndex).toUpperCase();
                            int opIndex = this.getOpIndex(content);
                            String startPos = content.substring(0, opIndex);
                            String colValue = startPos.substring(1, opIndex);
                            if (Integer.parseInt(colValue) - 1 == startRowNum)
                                cell.setCellType(Cell.CELL_TYPE_BLANK);
                        }
                    }
                }
            }
        }


    }

    /**
     * 生成填充模版标题数据
     *
     * @param exportInfo
     * @param wb
     * @param sheet
     * @throws Exception
     */
    private void generateTitleDatas(DataObject exportInfo, Workbook wb, Sheet sheet) throws Exception {
        int rows = sheet.getPhysicalNumberOfRows();
        CellStyle borderStyle = this.getBorderStyle(wb);
        CellStyle noneStyle = this.getNoneStyle(wb);
        for (int r = 0; r < rows; r++) {
            Row row = sheet.getRow(r);
            if (row != null) {

                int cells = row.getPhysicalNumberOfCells();
                for (short c = 0; c < cells; c++) {
                    Cell cell = row.getCell(c);
                    if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        String value = cell.getRichStringCellValue().getString();
                        if (value != null) {
                            value = value.trim();
                            if (value.startsWith(TITLE_FLAG)) {
                                value = value.substring(1);
                                //获取对应的值，支持XPATH取值
                                //Object obj=XPathLocator.newInstance().getValue(exportInfo, value);
                                //String content=obj+"";

                                String content = exportInfo.getString(value);
                                if (content == null) content = "";
                                //重建Cell，填充标题值
                                cell = row.createCell((short) c);
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cell.setCellValue(getRichTextString(content));

                                cell.setCellStyle(titleCellBold ? borderStyle : noneStyle);
                            }
                        }
                    }

                }
            }
        }
    }


    /**
     * 将指定的对象数组resulset输出到指定的Excel位置
     *
     * @param resultset List<DataObject>对象数组
     * @param wb        HSSFWorkbook
     * @param sheet     HSSFSheet
     */
    private void generateContentDatas(List<DataObject> resultset, Workbook wb, Sheet sheet) {
        CellStyle borderStyle = this.getBorderStyle(wb);
        CellStyle noneStyle = this.getNoneStyle(wb);

        //默认行号
        int autoRowId = 1;

        for (Iterator it = resultset.iterator(); it.hasNext(); autoRowId++) {
            DataObject content = (DataObject) it.next();
            Row sourceRow = sheet.getRow(startRow);
            Row row = sheet.createRow(startRow++);

            for (int i = 0; i < sourceRow.getPhysicalNumberOfCells(); i++) {
                //输出自动生成的行号
                if (fieldNames[i] != null && fieldNames[i].equals(FIELD_AUTO_ID)) {
                    Cell cell = row.createCell((short) i);
                    cell.setCellStyle(borderStyle);
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellValue(autoRowId);
                    continue;
                }

                if (fieldNames[i] != null) {
                    Cell cell = row.createCell((short) i);
                    cell.setCellStyle(borderStyle);
                    if (content != null) {
                        //字段名支持xpath取值
                        //Object value=XPathLocator.newInstance().getValue(content, fieldNames[i]);

                        Object value = content.get(fieldNames[i]);
                        if (value != null) {
                            if (value instanceof Double || value instanceof BigDecimal) {
                                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                                cell.setCellValue(Double.parseDouble(value.toString()));
                            } else {
                                cell.setCellType(Cell.CELL_TYPE_STRING);
                                cell.setCellValue(getRichTextString(value.toString()));
                            }
                        } else {
                            cell.setCellType(Cell.CELL_TYPE_BLANK);
                        }

                    } else {

                        cell.setCellType(Cell.CELL_TYPE_BLANK);
                        if (!blankCellBold) {
                            cell.setCellStyle(noneStyle);
                        } else {
                            cell.setCellStyle(borderStyle);
                        }
                    }
                } else {
                    Cell sourceCell = sourceRow.getCell((short) i);
                    if (sourceCell != null &&
                            sourceCell.getCellType() == Cell.CELL_TYPE_STRING &&
                            sourceCell.getRichStringCellValue().getString() != null &&
                            sourceCell.getRichStringCellValue().getString().toLowerCase().startsWith(FORMULA_FLAG)) {

                        Cell cell = row.createCell((short) i);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellValue(sourceCell.getRichStringCellValue());
                    }
                }
            }

            if (it.hasNext()) {
                //向下平推一行
                //sheet.shiftRows(startRow-1,sheet.getLastRowNum(),1);
                shiftDown(sheet, startRow - 1, sheet.getLastRowNum(), 1);
            }

        }
    }

    /**
     * 将结果集填充到Excel模版,resultset必须是以Map封装行
     *
     * @param
     * @param resultset 数据内容
     * @throws Exception
     */
    public void generate(OutputStream out, List<DataObject> resultset) throws Exception {
        this.generate(out, resultset, null);

    }

    /**
     * 将结果集填充到Excel模版,resultset必须是以Map封装行
     *
     * @param out       标题信息
     * @param resultSet 结果集
     * @throws Exception
     */
    public void generate(OutputStream out, List<DataObject> resultSet, DataObject exportInfo) throws Exception {
        Workbook wb = getCommonWorkbook(templateFile);
        Sheet sheet = wb.getSheetAt(0);
        initialize(sheet);
        if (startRow == -1)
            return;

        if (this.autoPagination) {
            this.generatePagination(wb, sheet, resultSet, exportInfo);
        } else if (this.autoSheet) {
            generatePaginationSheet(wb, sheet, resultSet, exportInfo);
        } else {
            //先填充标题
            if (exportInfo != null)
                this.generateTitleDatas(exportInfo, wb, sheet);
            //生成数据内容
            this.generateContentDatas(resultSet, wb, sheet);
            /*
            if(hasFormula){
            	this.calcFormula(wb,sheet);
            }
            */
        }
        wb.write(out);
        if (out != null) {
            out.close();
        }
    }

    /**
     * EXCEL分页
     * 必须在EXCEL模版的最后一行插入EXCEL分页符!
     *
     * @param wb          HSSFWorkbook
     * @param sourceSheet HSSFSheet
     * @param resultSet   填充数据集
     * @param exportInfo  信息栏内容
     * @throws Exception
     */
    private void generatePagination(Workbook wb, Sheet sourceSheet, List<DataObject> resultSet, DataObject exportInfo) throws Exception {
        int startPosition = startRow;
        tempStartRowNum = startRow;
        int count = resultSet.size() / maxrow;
        int rows = sourceSheet.getPhysicalNumberOfRows();
        count = fillingResultSet(resultSet, count);

        //删除最后一行的分页符
        try {
            sourceSheet.removeRowBreak(rows - 1);
        } catch (NullPointerException npe) {
            throw new Exception("指定的EXCEL模版文件[" + this.templateFile + "] 未插入分页符");
        }
        //超过1页则插入分页符
        for (int i = 1; i < count; i++) {
            //设置分页符
            sourceSheet.setRowBreak(i * rows - 1);
            this.copyRows(sourceSheet, sourceSheet, 0, rows, i * rows + 1);

        }
        if (exportInfo != null)
            this.generateTitleDatas(exportInfo, wb, sourceSheet);

        int current_page = 0;
        while (current_page < count) {
            List<DataObject> newList = resultSet.subList(current_page * maxrow, maxrow * (current_page + 1));
            this.generateContentDatas(newList, wb, sourceSheet);
            current_page++;
            //计算下一行的数据填充起始位置
            startRow = current_page * rows + maxrow + startPosition;
        }
        /*
        if(hasFormula)
        	this.calcFormula(wb,sourceSheet);
        */
    }


    /**
     * 生成分页的工作薄模版
     *
     * @param wb          HSSFWorkbook
     * @param sourceSheet HSSFSheet
     * @param resultSet   填充数据集
     * @param exportInfo  信息(标题)栏内容
     */
    private void generatePaginationSheet(Workbook wb, Sheet sourceSheet, List<DataObject> resultSet, DataObject exportInfo) throws Exception {
        int startPosition = startRow;
        int count = resultSet.size() / maxrow;

        count = fillingResultSet(resultSet, count);

        for (int i = 1; i < count; i++) {
            Sheet newSheet = wb.createSheet("Page " + i);
            this.copyRows(sourceSheet, newSheet, 0, sourceSheet.getLastRowNum(), 0);
        }

        if (count > 1) {
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                startRow = startPosition;
                List<DataObject> newList = resultSet.subList(i * maxrow, maxrow * (i + 1));
                Sheet sheet = wb.getSheetAt(i);
                //先填充标题
                if (exportInfo != null)
                    this.generateTitleDatas(exportInfo, wb, sheet);
                this.generateContentDatas(newList, wb, sheet);
                /*
                if(hasFormula)
                	this.calcFormula(wb,sheet);
                */
            }
        } else {
            Sheet sheet = wb.getSheetAt(0);
            if (exportInfo != null)
                this.generateTitleDatas(exportInfo, wb, sheet);

            this.generateContentDatas(resultSet, wb, sheet);
            /*
            if(hasFormula)
            	this.calcFormula(wb,sheet);
            */
        }
    }

    private int fillingResultSet(List<DataObject> resultSet, int count) {
        int num = resultSet.size() % maxrow;

        if (num > 0) {
            count = count + 1;
            num = maxrow - num;
            //不足指定的maxrow，添加空行
            for (int i = 0; i < num; i++) {
                resultSet.add(null);
            }

        }
        return count;
    }

    private CellStyle getBorderStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) fontSize);
        font.setFontName(fontName);
        style.setFont(font);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        return style;
    }

    private CellStyle getNoneStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) fontSize);
        font.setFontName(fontName);
        style.setFont(font);
        style.setBorderBottom(CellStyle.BORDER_NONE);
        style.setBorderLeft(CellStyle.BORDER_NONE);
        style.setBorderRight(CellStyle.BORDER_NONE);
        style.setBorderTop(CellStyle.BORDER_NONE);
        return style;
    }


    /**
     * 向下平推表格，并复制格式与内容
     *
     * @param thisrow：当前行号
     * @param lastrow：最后行号
     * @param shiftcount：平推量
     */
    private void shiftDown(Sheet sheet, int thisrow, int lastrow, int shiftcount) {
        sheet.shiftRows(thisrow, lastrow, shiftcount);

        for (int z = 0; z < shiftcount; z++) {
            Row row = sheet.getRow(thisrow);
            Row oldrow = sheet.getRow(thisrow + shiftcount);
            //将各行的行高复制
            oldrow.setHeight(row.getHeight());
            //将各个单元格的格式复制
            for (short i = 0; i <= oldrow.getPhysicalNumberOfCells(); i++) {

                Cell cell = row.createCell(i);
                Cell oldcell = oldrow.getCell(i);

                if (oldcell != null) {
                    switch (oldcell.getCellType()) {
                        case Cell.CELL_TYPE_STRING:
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            cell.setCellValue(oldcell.getRichStringCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(oldcell.getNumericCellValue());
                            break;
                        default:
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            cell.setCellValue(oldcell.getRichStringCellValue());

                    }
                    cell.setCellStyle(oldcell.getCellStyle());
                }
            }

            //将有列跨越的复制
            Vector regs = findRegion(sheet, oldrow);
            if (regs.size() != 0) {
                for (int i = 0; i < regs.size(); i++) {
                    CellRangeAddress reg = (CellRangeAddress) regs.get(i);
                    reg.setFirstRow(row.getRowNum());
                    reg.setLastRow(row.getRowNum());
                    sheet.addMergedRegion(reg);
                }
            }
            thisrow++;
        }
    }

    /**
     * 查找所有的合并单元格
     *
     * @param oldrow
     * @return
     */
    private Vector findRegion(Sheet sheet, Row oldrow) {
        Vector<CellRangeAddress> regs = new Vector<>();
        int num = sheet.getNumMergedRegions();
        int curRowid = oldrow.getRowNum();
        for (int i = 0; i < num; i++) {
            CellRangeAddress reg = sheet.getMergedRegion(i);
            if (reg.getFirstRow() == reg.getLastRow() && reg.getFirstRow() == curRowid) {
                regs.add(reg);
            }
        }
        return regs;
    }


    /**
     * 复制EXCEL行到指定的工作薄上
     * ××如果是分页显示，需要增加一个判断：当复制行包含公式forumla=sum(G7:G~)字样时候，必须修改其实行G7为相应的新行。
     *
     * @param sourceSheet 原工作薄
     * @param targetSheet 目标工作薄
     * @param pStartRow   复制起始行
     * @param pEndRow     复制终止行
     * @param pPosition   插入位置
     */
    private void copyRows(Sheet sourceSheet, Sheet targetSheet, int pStartRow, int pEndRow, int pPosition) {

        Row sourceRow = null;
        Row targetRow = null;
        Cell sourceCell = null;
        Cell targetCell = null;
        CellRangeAddress region = null;
        int cType;
        int i;
        short j;
        int targetRowFrom;
        int targetRowTo;

        if ((pStartRow == -1) || (pEndRow == -1)) {
            return;
        }
        // 拷贝合并的单元格
        for (i = 0; i < sourceSheet.getNumMergedRegions(); i++) {
            region = sourceSheet.getMergedRegion(i);
            if ((region.getFirstRow() >= pStartRow) && (region.getLastRow() <= pEndRow)) {
                targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
                targetRowTo = region.getLastRow() - pStartRow + pPosition;
                region.setFirstRow(targetRowFrom);
                region.setLastRow(targetRowTo);
                targetSheet.addMergedRegion(region);
            }
        }
        // 设置列宽
        for (i = pStartRow; i <= pEndRow; i++) {
            sourceRow = sourceSheet.getRow(i);
            if (sourceRow != null) {
                for (j = sourceRow.getFirstCellNum(); j < sourceRow.getLastCellNum(); j++) {
                    targetSheet.setColumnWidth(j, sourceSheet.getColumnWidth(j));
                }
                break;
            }
        }

        // 拷贝行并填充数据
        for (; i <= pEndRow; i++) {
            sourceRow = sourceSheet.getRow(i);
            if (sourceRow == null) {
                continue;
            }
            targetRow = targetSheet.createRow(i - pStartRow + pPosition);
            targetRow.setHeight(sourceRow.getHeight());
            for (j = sourceRow.getFirstCellNum(); j < sourceRow.getLastCellNum(); j++) {
                sourceCell = sourceRow.getCell(j);
                if (sourceCell == null) {
                    continue;
                }

                targetCell = targetRow.createCell(j);
                targetCell.setCellStyle(sourceCell.getCellStyle());
                cType = sourceCell.getCellType();
                targetCell.setCellType(cType);
                switch (cType) {
                    case Cell.CELL_TYPE_BOOLEAN:
                        targetCell.setCellValue(sourceCell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_ERROR:
                        targetCell.setCellErrorValue(sourceCell.getErrorCellValue());
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        targetCell.setCellFormula(parseFormula(sourceCell.getCellFormula()));
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        targetCell.setCellValue(sourceCell.getNumericCellValue());
                        break;
                    case Cell.CELL_TYPE_STRING:
                        targetCell.setCellValue(sourceCell.getRichStringCellValue());
                        break;
                }
                if (this.autoPagination) {
                    this.setFormulaBlankCell(sourceCell, tempStartRowNum);
                }
            }
        }
    }

    private String parseFormula(String pPOIFormula) {
        final String cstReplaceString = "ATTR(semiVolatile)"; //$NON-NLS-1$
        StringBuffer result = null;
        int index;
        result = new StringBuffer();
        index = pPOIFormula.indexOf(cstReplaceString);
        if (index >= 0) {
            result.append(pPOIFormula.substring(0, index));
            result.append(pPOIFormula.substring(index + cstReplaceString.length()));
        } else {
            result.append(pPOIFormula);
        }
        return result.toString();
    }

    private int getColumnIndex(char c) {
        int i = c;
        return i - 65;
    }

    private int getOpIndex(String s) {
        for (int i = 0; i < OP_FLAG.length; i++) {
            int index = s.indexOf(OP_FLAG[i]);
            if (index != -1) {
                return index;
            }
        }
        return -1;
    }

    /**
     * 判断是否无效Cell
     *
     * @param cell
     * @return
     */
    private boolean invalidCellValue(Cell cell) {
        if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return true;
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            if (cell.getRichStringCellValue().getString() == null || cell.getRichStringCellValue().getString().equals("")) {
                return true;
            }
        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            return true;
        }

        return false;
    }

    /**
     * 将目标Excel文件的内容导入到数据表
     *
     * @param targetFile Excel文件路径
     * @param entityName SDO数据实体全名
     * @return 返回1 导入成功
     * @throws Exception
     */
    public List<Object> importData(String targetFile, String entityName) throws Exception {
        Workbook wb = getCommonWorkbook(targetFile);
        List<Object> dataObjects = new ArrayList<Object>();
        for (int sheetCount = 0; sheetCount < wb.getNumberOfSheets(); sheetCount++) {
            Sheet sheet = wb.getSheetAt(sheetCount);
            int rows = sheet.getPhysicalNumberOfRows();
            initialize(sheet);
            if (startRow == -1)
                continue;

            //第一行为#字段名
            //第二行为字段标题，因此内容读取从startRow+2
            for (int rowCount = startRow + 2; rowCount < rows; rowCount++) {
                Row sourceRow = sheet.getRow(rowCount);
                //创建该类的对象实例，entityName为类的包路径下的全名
                Object obj_entity = Class.forName(entityName).newInstance();

                //判断某一行是否允许插入，当该行的所有列cell均为BLANK时不插入数据库
                boolean allowInsert = false;
                //以下构造导入的实体对象，并根据Excel单元格的内容填充实体属性值
                for (int cellCount = 0; cellCount < fieldNames.length; cellCount++) {

                    String propertyName = fieldNames[cellCount];
                    Cell cell = sourceRow.getCell((short) cellCount);

                    if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)
                        continue;
                    String value = null;

                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        if (DateUtil.isCellDateFormatted(cell)) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            value = dateFormat.format((cell.getDateCellValue()));

                        } else {
                            value = String.valueOf((long) cell.getNumericCellValue());
                        }
                    } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                        value = cell.getBooleanCellValue() + "";
                    } else {
                        value = cell.getRichStringCellValue().getString();
                    }
                    getObjectEntity(obj_entity, propertyName, value);


                }
                dataObjects.add(obj_entity);
            }
        }

        return dataObjects;
    }

    private void getObjectEntity(Object obj_entity, String propertyName, String value) throws NoSuchFieldException, IllegalAccessException {
        //同过JAVA反射，获取该列的属性
        Field fd = obj_entity.getClass().getDeclaredField(propertyName);
        fd.setAccessible(true);
        String propertyType = getType(fd.getType().getName());

        if (propertyType.equals("int")) {
            //防止可能出现的Excel表格读取自动加.号
            if (value.indexOf(".") != -1)
                value = value.substring(0, value.indexOf("."));
            fd.set(obj_entity, ChangeUtil.toInteger(value));//通过反射设置域值
        } else if (propertyType.equals("boolean")) {
            fd.set(obj_entity, ChangeUtil.toBoolean(Boolean.valueOf(value)));
        } else if (propertyType.equals("float")) {
            fd.set(obj_entity, ChangeUtil.toFloat(value));
        } else if (propertyType.equals("long")) {
            if (value.indexOf(".") != -1)
                value = value.substring(0, value.indexOf("."));
            fd.set(obj_entity, ChangeUtil.toLong(value));
        } else if (propertyType.equals("BigDecimal")) {
            fd.set(obj_entity, ChangeUtil.toBigDecimal(value));
        } else if (propertyType.equals("Date")) {
            fd.set(obj_entity, ChangeUtil.changeToDBDate(value));
        } else if (propertyType.equals("Timestamp")) {
            fd.set(obj_entity, ChangeUtil.toTimestamp(value));
        } else {
            fd.set(obj_entity, value);
        }
    }

    /**
     * 截取字段域类型
     *
     * @param str 例如：java.math.BigDecimal
     * @return String
     */
    public static String getType(String str) {
        int index = str.lastIndexOf(".");
        if (index == -1) {
            return str;
        } else {
            return str.substring(index + 1);
        }
    }

    /**
     * 如果模板文件是否存在
     *
     * @param templateFile 模板文件名
     * @return 文件存在返回true, 否则false
     * @throws IOException
     */
    protected boolean isExistTemplate(String templateFile) throws IOException {
        File file = new File(templateFile);
        return file.exists();

    }


    /**
     * 预初始化模板文件<BR>
     * 当用户指定的模板文件不存在时，将自动生成指定的模板文件，并第一行设置为要导出的字段列
     *
     * @throws Exception
     */
    /*
    public void prepareInitializeTemplate(String templateFile,DataObject dataObject) throws Exception{
    	 HSSFWorkbook wb = new HSSFWorkbook();
    	 FileOutputStream fileOut = new FileOutputStream(templateFile);
    	 HSSFSheet sheet = wb.createSheet("new sheet");
    	 //设置模板的第一行为输出字段定义列
    	 HSSFRow row = sheet.createRow((short)0);

    	 Object[] properties=dataObject.getType().getDeclaredProperties().toArray();
    	 for(int i=0;i<properties.length;i++){
    		 PropertyImpl property=(PropertyImpl)properties[i];
	    	 Cell cell = row.createCell((short)i);
	    	 HSSFRichTextString text=new HSSFRichTextString("#"+property.getName());
	    	 cell.setCellValue(text);
    	 }
    	 wb.write(fileOut);
    	 fileOut.close();

    }
   */
    public void checkExcelVersion(Workbook wb) throws IOException, InvalidFormatException {
        if(wb instanceof HSSFWorkbook){
            version = version_2003;
        }
        if(wb instanceof XSSFWorkbook){
            version = version_2007;
        }
        if(version.trim().length() == 0) {
            throw new IllegalArgumentException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
        }
    }

    private Workbook getCommonWorkbook(String fileName) throws Exception {
        InputStream inputStream = new FileInputStream(templateFile);
        Workbook wb = WorkbookFactory.create(inputStream);
        checkExcelVersion(wb);
        return wb;
    }

    private RichTextString getRichTextString(String value) {
        if (version_2003.equals(version)) {
            return new HSSFRichTextString(value);
        } else if (version_2007.equals(version)) {
            return new XSSFRichTextString(value);
        } else {
            throw new IllegalArgumentException("Your InputStream was neither an OLE2 stream, nor an OOXML stream");
        }
    }

    private static String version_2003 = "2003";
    private static String version_2007 = "2007";
}
