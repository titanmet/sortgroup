import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Sortgroup {

    public static String[] noDubl(String[] noDublArray) {
        int dups = 0;
        for (int i = 1; i < noDublArray.length; i++)
        {
            if (noDublArray[i].equals(noDublArray[i - 1]))
                dups++;
        }
        String[] returnArray = new String[noDublArray.length - dups];
        returnArray[0] = noDublArray[0];
        int count = 1;
        for (int i = 1; i < noDublArray.length; i++)
        {
                        if (!noDublArray[i].equals(noDublArray[i-1]))
            {
                returnArray[count] = noDublArray[i];
                count++;
            }
        }
        return returnArray;
    }


    public static void main(String[] args){

        String str="сапог семга сарай арбуз болт бокс биржа аэропорт филин аэлита сэр скоро";

        String[] word=str.split(" ");
        Arrays.sort(word);

        String[] firstLetter=new String[word.length];
        for (int i = 0; i < word.length; i++) {
            firstLetter[i]=word[i].substring(0,1);
        }
        Arrays.sort(firstLetter);

        List<String> wordList = new ArrayList<>(Arrays.asList(word));
        List<String> letterList = new ArrayList<>(Arrays.asList(firstLetter));
        Map<String, Long> letterMap = letterList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        letterList.removeIf(s -> letterMap.get(s).intValue() == 1);
        List<String> wordsList=new ArrayList<>();

        for (int j = 0; j < wordList.size(); j++) {
            for (int i =   0; i < letterList.size(); i++) {
                if (wordList.get(j).substring(0,1).equals(letterList.get(i)))
                    wordsList.add(wordList.get(j));
            }
        }

        String[] firstsLetter=new String[letterList.size()];
        for (int i = 0; i < letterList.size(); i++) {
            firstsLetter[i]=letterList.get(i);
        }

        String[] words = new String[wordsList.size()];
        for (int i = 0; i != wordsList.size(); i++) {
            words[i] = wordsList.get(i);
        }

        firstsLetter=noDubl(firstsLetter);
        words=noDubl(words);

        Comparator<String> stringLengthComparator = new StringLengthSort();
        Arrays.sort(words, stringLengthComparator);

        StringBuilder result=new StringBuilder();
        StringBuilder key=new StringBuilder();

        for (int i = 0; i < firstsLetter.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if ((firstsLetter[i].equals(words[j].substring(0, 1))))
                        result.append(words[j] + ",");
            }
            result.deleteCharAt(result.length()-1);
            key.append(firstsLetter[i]+"=");
            StringBuilder[][] c={{key},{result}};
            System.out.print(Arrays.deepToString(c)+"  ");
            key.delete(0,result.length());
            result.delete(0, result.length());

        }
    }
}