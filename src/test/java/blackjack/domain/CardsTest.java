package blackjack.domain;

import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardsTest {

    @ParameterizedTest(name = "[{index}] cards {0}, totalScore {1}")
    @MethodSource("parameters")
    @DisplayName("카드에 대한 점수 계산을 테스트한다.")
    void getTotalScoreBasicCard(List<Card> input, int totalScore) {
        Cards cards = new Cards(input);
        Assertions.assertThat(cards.getTotalScore()).isEqualTo(totalScore);
    }

    static Stream<Arguments> parameters() {
        return Stream.of(
                Arguments.of(List.of(new Card(Denomination.THREE, Suit.CLOVER),
                        new Card(Denomination.EIGHT, Suit.HEART)), 11),
                Arguments.of(List.of(new Card(Denomination.ACE, Suit.CLOVER),
                        new Card(Denomination.EIGHT, Suit.HEART)), 19),
                Arguments.of(List.of(new Card(Denomination.ACE, Suit.CLOVER),
                        new Card(Denomination.ACE, Suit.HEART)), 12),
                Arguments.of(List.of(new Card(Denomination.ACE, Suit.CLOVER),
                        new Card(Denomination.ACE, Suit.HEART),
                        new Card(Denomination.KING, Suit.HEART)), 12),
                Arguments.of(List.of(new Card(Denomination.ACE, Suit.CLOVER),
                        new Card(Denomination.JACK, Suit.HEART)), 21),
                Arguments.of(List.of(new Card(Denomination.ACE, Suit.CLOVER),
                        new Card(Denomination.FIVE, Suit.HEART),
                        new Card(Denomination.QUEEN, Suit.SPADE)), 16));
    }
}
