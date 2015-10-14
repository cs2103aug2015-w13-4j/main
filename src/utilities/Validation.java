class Validation {
    static boolean isValidString(String string) {
        if (string == null) {
            return false;
        }
        return true;
    }

    static boolean isValidInteger(Integer i) {
        if (i < 0) {
            return false;
        }
        return true;
    }

    static boolean isValidTaskField(String field) {
        if (field.equalsIgnoreCase("name") ||
            field.equalsIgnoreCase("date") ||
            field.equalsIgnoreCase("priority")) {
            return true;
        }
        return false;
    }
}
