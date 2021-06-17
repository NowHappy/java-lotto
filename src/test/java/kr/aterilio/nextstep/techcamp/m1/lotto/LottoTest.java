package kr.aterilio.nextstep.techcamp.m1.lotto;

import kr.aterilio.nextstep.techcamp.m1.utils.LottoParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoTest {

    @DisplayName("로또 생성 시 중복된 숫자가 있으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1, 2, 3, 4, 5, 1"})
    public void createLottoFailed_duplicated(String lottoNumbers) {
        assertThatThrownBy(() -> {
            new Lotto(LottoParser.parse(lottoNumbers));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }

    @DisplayName("입력받는 숫자의 갯수가 6개가 아니면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"4,3,2,1", "5, 2, 3", "1,2,3,4,5,6,7"})
    public void createLottoFailed_elementCount(String lottoNumbers) {
        assertThatThrownBy(() -> {
            new Lotto(LottoParser.parse(lottoNumbers));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개");
    }
}