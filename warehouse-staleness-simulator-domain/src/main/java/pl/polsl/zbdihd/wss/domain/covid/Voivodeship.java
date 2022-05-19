package pl.polsl.zbdihd.wss.domain.covid;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Voivodeship {

    DNSL,   //dolnośląskie
    KPOM,   //kujawsko-pomorskie
    LODZ,   //łódzkie
    LBEL,   //lubelskie
    LUBU,   //lubuskie
    MPOL,   //małopolskie
    MZWK,   //mazowieckie
    OPOL,   //opolskie
    PDKP,   //podkarpackie
    PDLK,   //podlaskie
    PMSK,   //pomorskie
    SLSK,   //śląskie
    SWKS,   //świętokrzyskie
    WMAZ,   //warmińsko-mazurskie
    WLKP,   //wielkopolskie
    ZPOM;   //zachodniopomorskie

    private static final Map<Integer, Voivodeship> VALUES = Stream.of(values())
                                                                  .collect(Collectors.toUnmodifiableMap(Enum::ordinal,
                                                                                                        Function.identity()));

    public static Voivodeship get(final int index) {
        return VALUES.get(index % VALUES.size());
    }

}
