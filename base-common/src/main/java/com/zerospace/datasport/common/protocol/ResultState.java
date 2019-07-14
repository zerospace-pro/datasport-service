package com.zerospace.datasport.common.protocol;

/**
 * <p>
 *
 * </p>
 *
 * @author nathan
 * @version 2019-07-13
 * @see Result
 */
public enum ResultState {
    ERROR(500),  //错误
    SUCCESS(200), //成功
    NO_AUTHORIZATION(403), //没有权限
    NO_RESOURCE(404); //没有找到资源

    private Integer value;

    private ResultState(int value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
