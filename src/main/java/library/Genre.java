package library;

import java.util.stream.Stream;

public enum Genre {

    NOT_DEFINED,
    FANTASY,
    FAIRY_TALES,
    NOVELS,
    HORROR,
    HISTORICAL,
    SCIENCE_FICTION;

    public static Genre toGenre(int value){
        return (value < 0 || value >= values().length) ? values()[0] : values()[value];
    }

    public static Boolean genreToPrint(){
        Stream.of(Genre.values()).forEach(value -> System.out.printf("(%d) %s%n", value.ordinal(), value));
        return true;
    }

}



