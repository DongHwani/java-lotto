package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottosTest {

    @DisplayName("Lottos 인스턴스 생성 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 6})
    public void createLottosInstance(int size) {
        //Given
        List<Lotto> lottoNumbers = new ArrayList<>();

        //When
        IntStream.range(0, size).forEach(i -> lottoNumbers.add(new Lotto(new LottoNumbers(new LottoAutoMachine()))));
        Lottos lottos = new Lottos(lottoNumbers);

        //Then
        assertThat(lottos.quantity()).isEqualTo(size);
    }

    @DisplayName("특정 index에 해당하는 Lotto 숫자를 반환하는 테스트")
    @Test
    public void indexGetLotto() {

        //Given
        List<Lotto> expected = new ArrayList<>();
        expected.add(0, new Lotto(new LottoNumbers(new LottoAutoMachine())));
        expected.add(1, new Lotto(new LottoNumbers(new LottoAutoMachine())));
        expected.add(2, new Lotto(new LottoNumbers((capacity) -> new TreeSet<>(Arrays.asList(10, 15, 16, 17, 18, 20)))));

        Lottos lottos = new Lottos(expected);

        //When
        LottoNumbers result = lottos.getLotto(expected.size() - 1);

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getLottoNumbers()).contains(10, 15, 16, 17, 18, 20);

    }


    @DisplayName("당첨번호 확인 후 Reward 인스턴스 생성 여부 테스트")
    @Test
    public void matchPrizeNumberTest() {

        //Given
        LottoShop lottoShop = new LottoShop();
        Lottos lottos = lottoShop.purchase(new LottoPrice(10000), (capacity -> new TreeSet<>(Arrays.asList(10, 15, 16, 17, 18, 20))));

        //When
        Reward reward = lottos.matchPrizeNumber(new PrizeLotto(new TreeSet<>(Arrays.asList(1, 3, 5, 7, 8, 9))));

        //Then
        assertThat(reward).isNotNull();

    }
}