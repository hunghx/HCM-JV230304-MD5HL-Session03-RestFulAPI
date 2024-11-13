package ra.demoapi.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDto<T> {
    private int code;
    private HttpStatus message;
    private T data;
}
