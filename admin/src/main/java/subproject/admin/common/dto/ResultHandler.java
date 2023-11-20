package subproject.admin.common.dto;

public class ResultHandler {

    public static Result<?> handle(final Integer status, final String msg, final Object data) {
        return Result.builder().status(status).msg(msg).data(data).build();
    }

}
