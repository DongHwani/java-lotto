package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {

    private static final int LOTTO_FIX_SIZE = 6;

    @DisplayName("로또 숫자 랜덤 생성 테스트")
    @Test
    public void lottoRandomCreateNumberTest() {
        //Given
        LottoMachine machine = (int capacity) -> Lotto.of(new HashSet<>(Arrays.asList(1, 3, 5, 6, 7, 8)));

        //When
        Lotto lotto = machine.createLotto(LOTTO_FIX_SIZE);

        //Then
        assertThat(lotto.getLottoNumbers()).hasSize(6);
        assertThat(lotto.getLottoNumbers()).contains(LottoNumber.from(1), LottoNumber.from(3), LottoNumber.from(5),
                                                    LottoNumber.from(6), LottoNumber.from(7), LottoNumber.from(8));
    }
}