package day04;

public class Passport {
    private final String byr;
    private final String iyr;
    private final String eyr;
    private final String hgt;
    private final String hcl;
    private final String ecl;
    private final String pid;
    private final String cid;

    public Passport(String byr, String iyr, String eyr, String hgt, String hcl, String ecl, String pid, String cid) {
        this.byr = byr;
        this.iyr = iyr;
        this.eyr = eyr;
        this.hgt = hgt;
        this.hcl = hcl;
        this.ecl = ecl;
        this.pid = pid;
        this.cid = cid;
    }

    public boolean hasAllRequiredFields() {
        return byr != null &&
                iyr != null &&
                eyr != null &&
                hgt != null &&
                hcl != null &&
                ecl != null &&
                pid != null;
    }

    public boolean isValid() {
        return hasAllRequiredFields() &&
                isValidYear(byr, 1920, 2002) &&
                isValidYear(iyr, 2010, 2020) &&
                isValidYear(eyr, 2020, 2030) &&
                isValidLength(hgt, 150, 193, 59, 76) &&
                isValidColor(hcl) &&
                isValidEyeColor(ecl) &&
                isValidNumberId(pid, 9);


    }

    private boolean isValidYear(String year, int lower, int upper) {
        if (year.length() != 4) {
            return false;
        }
        try {
            int yearInt = Integer.parseInt(year);
            return yearInt >= lower && yearInt <= upper;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidLength(String length, int lowerCm, int upperCm, int lowerIn, int upperIn) {
        String lengthAmountString = length.substring(0, length.length() - 2);
        int lengthAmount;
        try {
            lengthAmount = Integer.parseInt(lengthAmountString);
        } catch (NumberFormatException e) {
            return false;
        }
        String lengthUnit = length.substring(length.length() - 2);
        switch (lengthUnit) {
            case "cm":
                return lengthAmount >= lowerCm && lengthAmount <= upperCm;
            case "in":
                return lengthAmount >= lowerIn && lengthAmount <= upperIn;
            default:
                return false;
        }
    }

    private boolean isValidColor(String color) {
        return color.matches("^#([a-fA-F0-9]{6})$");
    }

    private boolean isValidEyeColor(String eyeColor) {
        return eyeColor.equals("amb") ||
                eyeColor.equals("blu") ||
                eyeColor.equals("brn") ||
                eyeColor.equals("gry") ||
                eyeColor.equals("grn") ||
                eyeColor.equals("hzl") ||
                eyeColor.equals("oth");
    }

    private boolean isValidNumberId(String numberString, int length) {
        if (numberString.length() != length) {
            return false;
        }
        try {
            int number = Integer.parseInt(numberString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
