package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {
    private final List<Integer> integersFixture = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

    @DisplayName("빈 생성자 호출시 size 6 의 List<LottoNumber> 멤버 변수를 가진 일급 컬렉션 Lotto를 생성한다.")
    @Test
    void create() {
        Lotto numbers = new Lotto();
        assertThat(numbers.getNumbers().stream().findFirst().get()).isInstanceOf(LottoNumber.class);
        assertThat(numbers.getNumbers().size()).isEqualTo(6);
    }

    @DisplayName("List<Integer> 를 전달 받아 Lotto를 생성한다.")
    @Test
    void createWithList() {
        Lotto numbers = new Lotto(integersFixture);
        assertThat(numbers.getNumbers().stream().findFirst().get()).isInstanceOf(LottoNumber.class);
    }

    @DisplayName("List<Integer> size가 6이 아닌 경우 IllegalArgumentException throw 한다.")
    @Test
    void createExceptionWithSize() {
        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        assertThatThrownBy(() -> new Lotto(integers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("size는 항상 6");
    }

    @DisplayName("1~45 사이 정수가 아닌 경우 IllegalArgumentException throw 한다.")
    @Test
    void createExceptionWithNumber() {
        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 50));
        assertThatThrownBy(() -> new Lotto(integers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("1~45 사이 값");
    }

    @DisplayName("match 함수는 prizeNumbers 와 일치하는 수를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"1, 6", "2, 5", "3, 4", "4, 3", "5, 2", "6, 1", "7, 0"})
    void match(int number, int expect) {
        List<Integer> prizeNumbers = Arrays.asList(number, number + 1, number + 2, number + 3, number + 4, number + 5);
        assertThat(new Lotto(integersFixture).match(new Lotto(prizeNumbers))).isEqualTo(expect);
    }
}
