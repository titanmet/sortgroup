import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.Comparator;

class StringLengthSort implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        if(o1.length() < o2.length()){
            return 1;
        }else{
            if(o1.length() > o2.length()){
                return -1;
            }else{
                return 0;
            }
        }
    }
}
class Main {

       public static void main(String[] args){

        String str="сапог семга сарай арбуз болт бокс биржа аэропорт филин аэлита сэр скоро завтрак зов лев клоун";

        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(str.split(" ")));
        List<String> letterList = new ArrayList<>();
        for (String s: wordList){ 
        letterList.add(s.substring(0,1));
        }
        Map<String, Long> letterMap = letterList.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        letterList.removeIf(p -> letterMap.get(p).intValue() == 1);
        List<String> wordsList=new ArrayList<>();
        for (int j = 0; j < wordList.size(); j++) {
            for (int i =   0; i < letterList.size(); i++) {
                if (wordList.get(j).substring(0,1).equals(letterList.get(i)))
                    wordsList.add(wordList.get(j));
            }
        }
        List<String> fs=new ArrayList<String>();
        for(String s: letterList)
        fs.add(s);
        List<String> wd=new ArrayList<String>();
        for(String s: wordsList)
        wd.add(s);
        List<String> firstsLetter = fs.stream().distinct().sorted().collect(Collectors.toList());
        List<String> words = wd.stream().distinct().sorted().collect(Collectors.toList());
        
        StringBuilder result=new StringBuilder();
        StringBuilder key=new StringBuilder();

        Map<StringBuilder,StringBuilder> group=new HashMap<>();
        System.out.print("[ ");
        for (int i = 0; i < firstsLetter.size(); i++) {
            for (int j = 0; j < words.size(); j++) {
                if ((firstsLetter.get(i).equals(words.get(j).substring(0, 1))))
                        result.append(words.get(j) + ",");
            }
            result.deleteCharAt(result.length()-1);
            key.append(firstsLetter.get(i)+"=");
            group.put(key,result);
            for (Map.Entry<StringBuilder,StringBuilder> item: group.entrySet())
                System.out.print(item.getKey()+"["+item.getValue()+"] ");
            key.delete(0,result.length());
            result.delete(0, result.length());
        }
        System.out.print("]");
    }
}
