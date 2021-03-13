public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> res = new LinkedListDeque<Character>();
        for(int i=0;i<word.length();i+=1){
            res.addLast(word.charAt(i));
        }
        return res;
    }
    private boolean helperisPalindrome(Deque a){
        if(a.size()==0 || a.size()==1){
            return true;
        }
        return a.removeFirst()==a.removeLast() && helperisPalindrome(a);
    }
    public boolean isPalindrome(String word){
        Deque<Character> res = wordToDeque(word);
        return helperisPalindrome(res);
    }
    private boolean helperisPalindrome(int start,int end,String word,CharacterComparator cc){
        if(start>=end){
            return true;
        }
        return cc.equalChars(word.charAt(start),word.charAt(end)) && helperisPalindrome(start+1,end-1,word,cc);
    }
    public boolean isPalindrome(String word,CharacterComparator cc){
        return helperisPalindrome(0,word.length()-1,word,cc);
    }


}
