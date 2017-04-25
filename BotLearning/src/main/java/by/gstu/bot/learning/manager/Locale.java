package by.gstu.bot.learning.manager;

/**
 * Created by Anna on 2/1/2016.
 */
public enum Locale {
    DEFAULT("en", "US") {
        public String getName() {
            return "-";
        }
    },
    EN_US("en", "US") {
        public String getName() {
            return "English";
        }
    },
    RU_RUS("ru", "RUS") {
        public String getName() {
            return "Russian";
        }
    };

    private final String language;
    private final String country;

    Locale(String language, String country) {
        this.language = language;
        this.country = country;
    }

    Locale() {
        this.language = "";
        this.country = "";
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public abstract String getName();

    public String toString() {
        return language + "_" + country;
    }
}
