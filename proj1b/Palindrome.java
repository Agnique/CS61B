public class Palindrome {
    static Palindrome palindrome = new Palindrome();

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        int n = word.length();
        for (int i = 0; i < n / 2; i++) {
            if (!wordDeque.get(i).equals(wordDeque.get(n - i - 1))) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        int n = word.length();
        for (int i = 0; i < n / 2; i++) {
            if (!cc.equalChars(wordDeque.get(i), wordDeque.get(n - i - 1))) {
                return false;
            }
        }
        return true;
    }
}
