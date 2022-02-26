package top.bluer.result.api;

/**
 * @program: bluer-tool
 * @description: 定义API返回码枚举类
 * @author: bluer
 * @date: 2022-02-24 18:51
 * codes: 扁鹊
 **/
public enum ResultCode {
    /*成功状态码*/
    SUCCESS(200, "操作成功"),

    /*失败状态码*/
    NOT_FOUND(404, "请求的资源不存在"),
    INTERNAL_ERROR(500, "系统内部错误"),
    PARAMETER_EXCEPTION(501, "请求参数校验异常"),
    ;

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return code;
    }

    public String message() {
        return message;
    }
}
