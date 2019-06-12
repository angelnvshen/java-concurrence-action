source

## 1 组件注册
  ### @configuration @bean 注册组件
  ### @componentScan 扫描指定包下的组件,组件上有（@Controller， @Service @Repository @Component）注解。
  ### @componentScan with customerTypeFilter (注意 useDefaultFilters = false ) 
  ### @Scope 
  默认 singleton，容器启动时创建对象，后续调用同一个对象时，从map中获取。
       prototype ,容器启动时不会创建对象，调用时再创建对象，多次调用，创建多次。
  ### @Lazy 
   @Lazy 容器启动时不会创建对象，调用时才创建
  ### @Conditional 按条件注册组件 
  ### @Import 导入组件，默认组件的名称是类的全类名  
    @ImportSelector 以数组的行式返回需要导入的组件
    @ImoprtBeanDefinitionRegisterar 可以引用 BeanDefineRegister 来导入需要引入的组件
  ### FactoryBean 导入组件，如果需要引用factoryBean 本身，需要加 &。