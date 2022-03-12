package blackjack.domain.player;

import java.util.Arrays;
import java.util.Objects;

public enum Choice {

    HIT("y"),
    STAY("n"),
    ;

    final String value;

    Choice(final String value) {
        this.value = value;
    }

    public static Choice from(final String inputValue) {
        return Arrays.stream(Choice.values())
                .filter(choice -> Objects.equals(choice.value, inputValue))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] y 또는 n만 입력할 수 있습니다."));
    }

    public boolean isHit() {
        return this == HIT;
    }
}
