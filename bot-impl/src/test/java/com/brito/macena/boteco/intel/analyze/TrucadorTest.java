package com.brito.macena.boteco.intel.analyze;

import com.brito.macena.boteco.utils.Status;
import com.bueno.spi.model.CardRank;
import com.bueno.spi.model.CardSuit;
import com.bueno.spi.model.GameIntel;
import com.bueno.spi.model.TrucoCard;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Trucador tests")
public class TrucadorTest {
    @BeforeAll
    static void setupAll() { System.out.println("Starting Trucador tests..."); }

    @AfterAll
    static void tearDownAll() { System.out.println("Finishing Trucador tests..."); }

    @Nested
    @DisplayName("Three Cards Handler Tests")
    class ThreeCardsHandlerTests {

        @Test
        @DisplayName("Returns EXCELLENT when having at least two manilhas with three cards")
        void returnsExcellentWhenHavingAtLeastTwoManilhasWithThreeCards() {
            GameIntel intel = GameIntel.StepBuilder.with()
                    .gameInfo(List.of(), List.of(), TrucoCard.of(CardRank.ACE, CardSuit.CLUBS), 0)
                    .botInfo(List.of(
                            TrucoCard.of(CardRank.ACE, CardSuit.CLUBS),
                            TrucoCard.of(CardRank.ACE, CardSuit.SPADES),
                            TrucoCard.of(CardRank.THREE, CardSuit.DIAMONDS)
                    ), 0)
                    .opponentScore(0)
                    .build();
            Trucador trucador = new Trucador(intel);
            assertEquals(Status.EXCELLENT, trucador.threeCardsHandler(intel.getCards()));
        }

        @Test
        @DisplayName("Returns GOOD when having one manilha and second best card value is 9 with three cards")
        void returnsGoodWhenHavingOneManilhaAndSecondBestCardValueIsNineWithThreeCards() {
            GameIntel intel = GameIntel.StepBuilder.with()
                    .gameInfo(List.of(), List.of(), TrucoCard.of(CardRank.ACE, CardSuit.CLUBS), 0)
                    .botInfo(List.of(
                            TrucoCard.of(CardRank.ACE, CardSuit.CLUBS),
                            TrucoCard.of(CardRank.TWO, CardSuit.SPADES),
                            TrucoCard.of(CardRank.THREE, CardSuit.DIAMONDS)
                    ), 0)
                    .opponentScore(0)
                    .build();
            Trucador trucador = new Trucador(intel);
            assertEquals(Status.GOOD, trucador.threeCardsHandler(intel.getCards()));
        }
    }
}
