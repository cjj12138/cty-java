package com.exam.demo.constant;

/**
 * @program : report
 * @description : 常量类
 * @author: xuyaohui
 * @create: 2019-11-01-10
 */
public final class CommonConstants {

    public static final String REDIS_PREFIX = "login_";
    public static final String REDIS_MSG_ALERT = "msg_alert_";

    public static final Integer SUCCESS = 2000;

    public static final Integer FAIL = 1;

    public static final Integer TOKEN_EXPIRE_OR_ERROR = -1;



    /**
     * 用户数据类型
     * 1: 绘制，2： excel导入， 3：接口导入 ,4:定制化需求 ， 5：管理员上传
     */
    public static final Integer DRAW_TYPE = 1;
    public static final Integer EXCEL_IMPORT_TYPE = 2;
    public static final Integer INTERFACE_IMPORT_TYPE = 3;
    public static final Integer ADMIN_EXCEL_IMPORT_TYPE=5;
    public static final Integer DEMAND_CUSTOMIZATION=4;


    public static final String SELECT_ERROR ="查询失败";
    public static final String SELECT_SUCCESS ="查询成功";

    public static final String INSERT_ERROR ="插入失败";
    public static final String INSERT_SUCCESS ="插入成功";

    public static final String DELETE_ERROR ="删除失败";
    public static final String DELETE_SUCCESS ="删除成功";

    public static final String UPDATE_ERROR ="更新失败";
    public static final String UPDATE_SUCCESS ="更新成功";


    /**
     * shiro认证或授权错误
     */
    public static final String REQUEST_ERROR = "请求异常，请检查参数后再次重试";
    public static final String SHIRO_ERROR = "认证或授权异常，请检查是否有相应权限";
    public static final String TOKEN_ERROR = "token无效";

    /**
     * 用户相关操作
     */
    public static final String PARAMS_ERROR = "参数错误，请检查参数后再次重试";
    public static final String USER_OR_PASSWORD_NOT_NULL = "账号或密码不能为空";
    public static final String USER__NOT_EXISTS = "账号不存在";
    public static final String USER_OR_PASSWORD_ERROR = "账号或密码错误";
    public static final String PLZ_SELECT_ADM = "账号或密码错误";

    /**
     * 任务
     */
    public static final String TASK_NOT_EXISTS = "任务不存在";
    public static final String TASK_SUBMIT = "任务创建成功";
    public static final String TASK_NULL_DATA = "数据不存在";


    public static final String FILE_PATH = "/app/ituc/shapeFile/";
     //public static final String FILE_PATH = "/app/ituc-gray-back/shapeFile/";
//    public static final String FILE_PATH = "/app/ituc-gray-back/";
  // public static final String FILE_PATH = "D://app/ituc-gray-back/";
   //public static final String FILE_PATH = "/Users/zhengwenqiang/Desktop/shp/";

}
