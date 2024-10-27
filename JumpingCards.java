import java.util.*;

public class JumpingCards {
    static HashSet<String> playboard = new HashSet<>(); // Карты на игровом поле
    static HashSet<String> playerCards = new HashSet<>(); // Карты игрока
    static HashSet<String> computerCards = new HashSet<>(); // Карты компьютера

    static final String[] CARD_SUITS = {"C", "H", "D", "S"};
    static Random rand = new Random();

    // Метод генерации уникальных карт на игровом поле
    public static void generateUniqueCards() {
        playboard.clear();
        while (playboard.size() < 6) {
            String suit = CARD_SUITS[rand.nextInt(CARD_SUITS.length)];
            int number = rand.nextInt(13) + 1; // Диапазон 1-13
            playboard.add(suit + number);
        }
    }

    // Метод, имитирующий бросок кубика и выбор карты для игрока
    public static void rollDieAndAssignCardToPlayer() {
        while (playerCards.size() < 3) {
            int dieValue = rand.nextInt(6) + 1; // Генерируем число от 1 до 6
            String selectedCard = (String) playboard.toArray()[dieValue - 1];
            if (!playerCards.contains(selectedCard)) {
                playerCards.add(selectedCard);
            }
        }
    }

    // Метод для выбора карт для компьютера (оставшиеся карты)
    public static void assignRemainingCardsToComputer() {
        for (String card : playboard) {
            if (!playerCards.contains(card) && computerCards.size() < 3) {
                computerCards.add(card);
            }
        }
    }

    // Метод для подсчёта очков (сумма числовых значений карт)
    public static int calculateScore(Set<String> cards) {
        int score = 0;
        for (String card : cards) {
            score += Integer.parseInt(card.substring(1)); // Берём числовую часть карты
        }
        return score;
    }

    // Метод для отображения конечного результата игры
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
