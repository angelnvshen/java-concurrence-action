package own.stu.tkmybatis.demo.common.util;

public class WaterWallResponseEntity{
        private int response;
        private int evil_level;
        private String err_msg;

        public int getResponse() {
            return response;
        }

        public void setResponse(int response) {
            this.response = response;
        }

        public int getEvil_level() {
            return evil_level;
        }

        public void setEvil_level(int evil_level) {
            this.evil_level = evil_level;
        }

        public String getErr_msg() {
            return err_msg;
        }

        public void setErr_msg(String err_msg) {
            this.err_msg = err_msg;
        }
    }