package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class Tuple<T1, T2> implements Serializable {

    private T1 left;
    private T2 right;

}
