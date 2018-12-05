package urlshortener.demo.exception;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2018-11-26T14:20:22.002Z[GMT]")

public class UnknownEntityException extends RuntimeApiException{
    private int code;
    public UnknownEntityException(int code, String msg) {
        super(code, msg);
        this.code = code;
    }
}
