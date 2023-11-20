package subproject.admin.common.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class Result<T> {

    private Integer status;
    private String msg;
    private T data;
    private String errorName;



    @Builder
    public Result(final Integer status, final String msg, final T data, final String errorName) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.errorName = errorName;
    }
}
