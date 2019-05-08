import javax.xml.bind.annotation.XmlType;
import java.util.LinkedList;
import java.util.List;

public class SeperateChainingHashTable<E>
{
    private static final int DEFAULT_TABLE_SIZE = 101;
    private List<E>[] theLists;
    private int currentSize;

    public SeperateChainingHashTable()
    {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeperateChainingHashTable(int size)
    {

        theLists = new LinkedList[nextPrime( size )];
        for( int i = 0; i < theLists.length; i++){
            theLists[i] = new LinkedList<>();
        }
    }


    public void insert(E x){
        List<E> whichList = theLists[ myhash(x)];
        if( !whichList.contains(x)){
            whichList.add(x);

            if(++currentSize > theLists.length){
                rehash();
            }
        }
    }

    private void rehash(){
        List<E> [] oldLists = theLists;

        theLists = new List[nextPrime(2 * theLists.length)];
        for(int j = 0; j < theLists.length; j++){
            theLists[j] = new LinkedList<>();
        }

        currentSize = 0;
        for(int i = 0; i < oldLists.length; i++){
            for( E item : oldLists[i])
                insert(item);
        }
    }

    public void remove(E x){
        List<E> whichList = theLists[myhash(x)];
        if(whichList.contains(x)){
            whichList.remove(x);
            currentSize--;
        }
    }

    public boolean contains(E x){
        List<E> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }

    public void makeEmpty(){
        for(int i = 0; i < theLists.length; i++){
            theLists[i].clear();
        }
        currentSize = 0;
    }

    private int myhash(E x){
        int hashVal = x.hashCode();

        hashVal %= theLists.length;
        if(hashVal < 0)
            hashVal += theLists.length;

        return hashVal;
    }

    private static int nextPrime(int n){
        return 0;
    }

    private static boolean isPrime(int n){
        return false;
    }
}
