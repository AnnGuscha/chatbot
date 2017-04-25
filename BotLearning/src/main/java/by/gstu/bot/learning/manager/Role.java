package by.gstu.bot.learning.manager;

import java.util.Arrays;

public enum Role {
    ANONYMOUS(0) {
        public String toString() {
            return "Anonymous";
        }
    },
    ADMIN(1) {
        public String toString() {
            return "Administrator";
        }
    },
    EXPERT(2) {
        public String toString() {
            return "Expert";
        }
    },
    USER(3) {
        public String toString() {
            return "User";
        }
    };

    private final int value;

    Role(int value) {
        this.value = value;
    }

    public static Role getRole(int value) {
        return Arrays.asList(Role.values()).stream().filter(role -> role.getValue() == value).findFirst().get();
    }

    public int getValue() {
        return value;
    }

    public abstract String toString();
}