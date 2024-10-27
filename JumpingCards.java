import java.util.*;

public class JumpingCards {
    static HashSet<String> playboard = new HashSet<>();
    static HashSet<String> playerCards = new HashSet<>();
    static HashSet<String> computerCards = new HashSet<>();

    static final String[] CARD_SUITS = {"C", "H", "D", "S"};
    static Random rand = new Random();

    public static void generateUniqueCards() {
        playboard.clear();
        while (playboard.size() < 6) {
            String suit = CARD_SUITS[rand.nextInt(CARD_SUITS.length)];
            int number = rand.nextInt(13) + 1;
            playboard.add(suit + number);
        }
    }

    public static void rollDieAndAssignCardToPlayer() {
        while (playerCards.size() < 3) {
            int dieValue = rand.nextInt(6) + 1;
            String selectedCard = (String) playboard.toArray()[dieValue - 1];
            if (!playerCards.contains(selectedCard)) {
                playerCards.add(selectedCard);
            }
        }
    }

    public static void assignRemainingCardsToComputer() {
        for (String card : playboard) {
            if (!playerCards.contains(card) && computerCards.size() < 3) {
                computerCards.add(card);
            }
        }
    }

    public static int calculateScore(Set<String> cards) {
        int score = 0;
        for (String card : cards) {
            score += Integer.parseInt(card.substring(1));
        }
        return score;
    }

    public static String determineWinner() {
        int playerScore = calculateScore(playerCards);
        int computerScore = calculateScore(computerCards);

        System.out.println("Player cards: " + playerCards + " | Score: " + playerScore);
        System.out.println("Computer cards: " + computerCards + " | Score: " + computerScore);

        if (playerScore > computerScore) {
            return "Player wins!";
        } else if (playerScore < computerScore) {
            return "Computer wins!";
        } else {
            return "It's a draw!";
        }
    }

    public static void main(String[] args) {
        System.out.println("- JUMPING CARDS -\nWelcome to the game!\n");
        generateUniqueCards();
        rollDieAndAssignCardToPlayer();
        assignRemainingCardsToComputer();

        String result = determineWinner();
        System.out.println(result);
    }
}
