package common.protocol;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 <p>
 通用的数据响应协议
 </p>
 @author nathan
 @version 2019-07-13 */
@Data
@ApiModel(value = "接口返回对象", description = "接口返回对象")
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -7716703690996912316L;

    /**
     成功标志
     */
    @ApiModelProperty(value = "成功标志")
    private boolean success = true;

    /**
     返回处理消息
     */
    @ApiModelProperty(value = "返回处理消息")
    private String message = "操作成功";

    /**
     返回代码
     */
    @ApiModelProperty(value = "返回代码")
    private ResultState code = ResultState.SUCCESS;

    /**
     返回数据对象 data
     */
    @ApiModelProperty(value = "返回数据对象")
    private T result;

    public Result() {

    }


    /**
     时间戳
     */
    @ApiModelProperty(value = "时间戳")
    private long timestamp = System.currentTimeMillis();

    public static Result<Object> error(ResultState state, Throwable t) {
        return error(state, t.getMessage());
    }

    public static Result<Object> error(String msg) {
        return error(ResultState.ERROR, msg);
    }

    public static Result<Object> error(ResultState state, String msg) {
        Result<Object> r = new Result<>();
        r.setCode(state);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public static Result<Object> ok() {
        return new Result<>();
    }

    public static Result<Object> ok(String msg) {
        Result<Object> r = new Result<>();
        r.setMessage(msg);
        return r;
    }

    public static Result<Object> ok(Object data) {
        Result<Object> r = new Result<>();
        r.setResult(data);
        return r;
    }
}
