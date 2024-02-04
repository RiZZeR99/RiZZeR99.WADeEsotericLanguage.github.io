package ela.module_1.elafinder.api;

public class NotFoundException extends ApiException {
    private final int code;

    public NotFoundException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
