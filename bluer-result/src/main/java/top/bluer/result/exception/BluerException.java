package top.bluer.result.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import top.bluer.result.api.ResultCode;

/**
 * @program: bluer-tool
 * @description: 自定义异常基类
 * @author: bluer
 * @date: 2022-02-24 18:53
 * codes: 扁鹊
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class BluerException extends RuntimeException {
    private static final int BASE_EXCEPTION_CODE = ResultCode.INTERNAL_ERROR.code();
    private static final String BASE_EXCEPTION_MESSAGE = ResultCode.INTERNAL_ERROR.message();

    private Integer code;
    private String message;

    public BluerException() {
        super(BASE_EXCEPTION_MESSAGE);
        this.code = BASE_EXCEPTION_CODE;
        this.message = BASE_EXCEPTION_MESSAGE;
    }

    public BluerException(String message) {
        super(message);
        this.code = BASE_EXCEPTION_CODE;
        this.message = message;
    }

    public BluerException(ResultCode resultCode) {
        super(resultCode.message());
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public BluerException(Throwable cause) {
        super(cause);
        this.code = BASE_EXCEPTION_CODE;
        this.message = BASE_EXCEPTION_MESSAGE;
    }

    public BluerException(String message, Throwable cause) {
        super(message, cause);
        this.code = BASE_EXCEPTION_CODE;
        this.message = message;
    }

    public BluerException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BluerException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }
}
