package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerTest {

    @ParameterizedTest(name = "[{index}] name \"{0}\"")
    @ValueSource(strings = {"", " "})
    @DisplayName("플레이어 이름이 공백일 경우 예외를 발생시킨다.")
    void checkNameBlank(String name) {
        CardDeck cardDeck = new CardDeck();
        List<Card> cards = Arrays.asList(cardDeck.getCard(1), cardDeck.getCard(2));
        Assertions.assertThatThrownBy(() -> new Player(name, cards) {
                    @Override
                    public boolean canAddCard() {
                        return false;
                    }
                })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름은 공백일 수 없습니다.");
    }

    @ParameterizedTest(name = "[{index}] 카드 size {0}")
    @ValueSource(ints = {1, 3, 4})
    @DisplayName("가장 처음에 받은 카드가 2장이 아닐 경우 예외를 발생시킨다.")
    void checkInitialCardsSize(int size) {
        CardDeck cardDeck = new CardDeck();
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            cards.add(cardDeck.getCard(i));
        }

        Assertions.assertThatThrownBy(() -> new Player("엘리", cards) {
                    @Override
                    public boolean canAddCard() {
                        return false;
                    }
                })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 가장 처음에는 카드를 2장씩 나눠줘야 합니다.");
    }

    @ParameterizedTest(name = "[{index}] name {0}")
    @ValueSource(strings = {"13!", "123@", "#asd", "$wqe", "qwer%$"})
    @DisplayName("플레이어 이름에 특수문자가 들어갈 경우 예외를 발생시킨다.")
    void checkNameSpecialCharacters(String name) {
        CardDeck cardDeck = new CardDeck();
        List<Card> cards = Arrays.asList(cardDeck.getCard(1), cardDeck.getCard(2));
        Assertions.assertThatThrownBy(() -> new Player(name, cards) {
                    @Override
                    public boolean canAddCard() {
                        return false;
                    }
                })
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이름에는 특수문자가 들어갈 수 없습니다.");
    }
}
