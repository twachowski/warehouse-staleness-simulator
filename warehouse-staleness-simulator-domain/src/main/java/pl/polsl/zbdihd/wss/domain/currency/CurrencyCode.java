package pl.polsl.zbdihd.wss.domain.currency;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * ISO 4217 currency codes
 */
public enum CurrencyCode {

    AED,    // United Arab Emirates dirham
    AFN,    // Afghan afghani
    ALL,    // Albanian lek
    AMD,    // Armenian dram
    ANG,    // Netherlands Antillean guilder
    AOA,    // Angolan kwanza
    ARS,    // Argentine peso
    AUD,    // Australian dollar
    AWG,    // Aruban florin
    AZN,    // Azerbaijani manat
    BAM,    // Bosnia and Herzegovina convertible mark
    BBD,    // Barbados dollar
    BDT,    // Bangladeshi taka
    BGN,    // Bulgarian lev
    BHD,    // Bahraini dinar
    BIF,    // Burundian franc
    BMD,    // Bermudian dollar
    BND,    // Brunei dollar
    BOB,    // Boliviano
    BOV,    // Bolivian Mvdol (funds code)
    BRL,    // Brazilian real
    BSD,    // Bahamian dollar
    BTN,    // Bhutanese ngultrum
    BWP,    // Botswana pula
    BYN,    // Belarusian ruble
    BZD,    // Belize dollar
    CAD,    // Canadian dollar
    CDF,    // Congolese franc
    CHE,    // WIR euro (complementary currency)
    CHF,    // Swiss franc
    CHW,    // WIR franc (complementary currency)
    CLF,    // Unidad de Fomento (funds code)
    CLP,    // Chilean peso
    CNY,    // Chinese yuan[8]
    COP,    // Colombian peso
    COU,    // Unidad de Valor Real (UVR) (funds code)[9]
    CRC,    // Costa Rican colon
    CUC,    // Cuban convertible peso
    CUP,    // Cuban peso
    CVE,    // Cape Verdean escudo
    CZK,    // Czech koruna
    DJF,    // Djiboutian franc
    DKK,    // Danish krone
    DOP,    // Dominican peso
    DZD,    // Algerian dinar
    EGP,    // Egyptian pound
    ERN,    // Eritrean nakfa
    ETB,    // Ethiopian birr
    EUR,    // Euro
    FJD,    // Fiji dollar
    FKP,    // Falkland Islands pound
    GBP,    // Pound sterling
    GEL,    // Georgian lari
    GHS,    // Ghanaian cedi
    GIP,    // Gibraltar pound
    GMD,    // Gambian dalasi
    GNF,    // Guinean franc
    GTQ,    // Guatemalan quetzal
    GYD,    // Guyanese dollar
    HKD,    // Hong Kong dollar
    HNL,    // Honduran lempira
    HRK,    // Croatian kuna
    HTG,    // Haitian gourde
    HUF,    // Hungarian forint
    IDR,    // Indonesian rupiah
    ILS,    // Israeli new shekel
    INR,    // Indian rupee
    IQD,    // Iraqi dinar
    IRR,    // Iranian rial
    ISK,    // Icelandic króna (plural: krónur)
    JMD,    // Jamaican dollar
    JOD,    // Jordanian dinar
    JPY,    // Japanese yen
    KES,    // Kenyan shilling
    KGS,    // Kyrgyzstani som
    KHR,    // Cambodian riel
    KMF,    // Comoro franc
    KPW,    // North Korean won
    KRW,    // South Korean won
    KWD,    // Kuwaiti dinar
    KYD,    // Cayman Islands dollar
    KZT,    // Kazakhstani tenge
    LAK,    // Lao kip
    LBP,    // Lebanese pound
    LKR,    // Sri Lankan rupee
    LRD,    // Liberian dollar
    LSL,    // Lesotho loti
    LYD,    // Libyan dinar
    MAD,    // Moroccan dirham
    MDL,    // Moldovan leu
    MGA,    // Malagasy ariary
    MKD,    // Macedonian denar
    MMK,    // Myanmar kyat
    MNT,    // Mongolian tögrög
    MOP,    // Macanese pataca
    MRU,    // Mauritanian ouguiya
    MUR,    // Mauritian rupee
    MVR,    // Maldivian rufiyaa
    MWK,    // Malawian kwacha
    MXN,    // Mexican peso
    MXV,    // Mexican Unidad de Inversion (UDI) (funds code)
    MYR,    // Malaysian ringgit
    MZN,    // Mozambican metical
    NAD,    // Namibian dollar
    NGN,    // Nigerian naira
    NIO,    // Nicaraguan córdoba
    NOK,    // Norwegian krone
    NPR,    // Nepalese rupee
    NZD,    // New Zealand dollar
    OMR,    // Omani rial
    PAB,    // Panamanian balboa
    PEN,    // Peruvian sol
    PGK,    // Papua New Guinean kina
    PHP,    // Philippine peso[13]
    PKR,    // Pakistani rupee
    PLN,    // Polish złoty
    PYG,    // Paraguayan guaraní
    QAR,    // Qatari riyal
    RON,    // Romanian leu
    RSD,    // Serbian dinar
    RUB,    // Russian ruble
    RWF,    // Rwandan franc
    SAR,    // Saudi riyal
    SBD,    // Solomon Islands dollar
    SCR,    // Seychelles rupee
    SDG,    // Sudanese pound
    SEK,    // Swedish krona (plural: kronor)
    SGD,    // Singapore dollar
    SHP,    // Saint Helena pound
    SLL,    // Sierra Leonean leone
    SOS,    // Somali shilling
    SRD,    // Surinamese dollar
    SSP,    // South Sudanese pound
    STN,    // São Tomé and Príncipe dobra
    SVC,    // Salvadoran colón
    SYP,    // Syrian pound
    SZL,    // Swazi lilangeni
    THB,    // Thai baht
    TJS,    // Tajikistani somoni
    TMT,    // Turkmenistan manat
    TND,    // Tunisian dinar
    TOP,    // Tongan paʻanga
    TRY,    // Turkish lira
    TTD,    // Trinidad and Tobago dollar
    TWD,    // New Taiwan dollar
    TZS,    // Tanzanian shilling
    UAH,    // Ukrainian hryvnia
    UGX,    // Ugandan shilling
    USD,    // United States dollar
    USN,    // United States dollar (next day) (funds code)
    UYI,    // Uruguay Peso en Unidades Indexadas (URUIURUI) (funds code)
    UYU,    // Uruguayan peso
    UYW,    // Unidad previsional[15]
    UZS,    // Uzbekistan som
    VED,    // Venezuelan bolívar digital[16]
    VES,    // Venezuelan bolívar soberano[13]
    VND,    // Vietnamese đồng
    VUV,    // Vanuatu vatu
    WST,    // Samoan tala
    XAF,    // CFA franc BEAC
    XAG,    // Silver (one troy ounce)
    XAU,    // Gold (one troy ounce)
    XBA,    // European Composite Unit (EURCO) (bond market unit)
    XBB,    // European Monetary Unit (E.M.U.-6) (bond market unit)
    XBC,    // European Unit of Account 9 (E.U.A.-9) (bond market unit)
    XBD,    // European Unit of Account 17 (E.U.A.-17) (bond market unit)
    XCD,    // East Caribbean dollar
    XDR,    // Special drawing rights
    XOF,    // CFA franc BCEAO
    XPD,    // Palladium (one troy ounce)
    XPF,    // CFP franc (franc Pacifique)
    XPT,    // Platinum (one troy ounce)
    XSU,    // SUCRE
    XTS,    // Code reserved for testing
    XUA,    // ADB Unit of Account
    XXX,    // No currency
    YER,    // Yemeni rial
    ZAR,    // South African rand
    ZMW,    // Zambian kwacha
    ZWL;    // Zimbabwean dollar

    private static final Map<Integer, CurrencyCode> VALUES = Stream.of(values())
                                                                   .collect(Collectors.toUnmodifiableMap(Enum::ordinal,
                                                                                                         Function.identity()));

    public static CurrencyCode get(final int index) {
        return VALUES.get(index % VALUES.size());
    }

}
