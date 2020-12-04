fun main() {
    val input = readInput("/day04.txt").toPassports()
    println("Result part 1: ${day04part1(input)}")
    println("Result part 2: ${day04part2(input)}")
}


fun day04part1(passports: List<Passport>): Int {
    return passports.filter { it.fieldsPresent() }.count()
}

fun day04part2(passports: List<Passport>): Int {
    return passports.filter { it.isValid() }.count()
}

fun String.toPassports(): List<Passport> {
    return this.split(Regex("(?:\\r\\n){2}"))
        .map { block ->
            val line = block.replace(Regex("\\r\\n"), " ")
            var byr: String? = null
            var iyr: String? = null
            var eyr: String? = null
            var hgt: String? = null
            var hcl: String? = null
            var ecl: String? = null
            var pid: String? = null

            Regex("(?<key>[a-z]{3}):(?<value>[a-z0-9#]+)")
                .findAll(line)
                .forEach { match ->
                    when (match.groups["key"]?.value) {
                        "byr" -> byr = match.groups["value"]?.value
                        "iyr" -> iyr = match.groups["value"]?.value
                        "eyr" -> eyr = match.groups["value"]?.value
                        "hgt" -> hgt = match.groups["value"]?.value
                        "hcl" -> hcl = match.groups["value"]?.value
                        "ecl" -> ecl = match.groups["value"]?.value
                        "pid" -> pid = match.groups["value"]?.value
                    }
                }

            Passport(byr, iyr, eyr, hgt, hcl, ecl, pid)
        }
}

data class Passport(
    val byr: String?, val iyr: String?, val eyr: String?, val hgt: String?, val hcl: String?, val ecl: String?,
    val pid: String?) {

    private val hgtPattern = Regex("(?<value>[0-9]+)(?<unit>cm|in)")
    private val hclPattern = Regex("#[0-9a-f]{6}")
    private val eclPattern = Regex("amb|blu|brn|gry|grn|hzl|oth")
    private val pidPattern = Regex("[0-9]{9}")

    fun fieldsPresent(): Boolean {
        return !byr.isNullOrBlank()
                && !iyr.isNullOrBlank()
                && !eyr.isNullOrBlank()
                && !hgt.isNullOrBlank()
                && !hcl.isNullOrBlank()
                && !ecl.isNullOrBlank()
                && !pid.isNullOrBlank()
    }

    fun isValid(): Boolean {
        val byrInt = byr?.toInt() ?: 0
        val iyrInt = iyr?.toInt() ?: 0
        val eyrInt = eyr?.toInt() ?: 0
        val hgtMatch = hgtPattern.find(hgt ?: "")
        val hgtVal = hgtMatch?.groups?.get("value")?.value?.toInt() ?: 0
        val hgtValid = when (hgtMatch?.groups?.get("unit")?.value) {
            "cm" -> hgtVal in 150..193
            "in" -> hgtVal in 59..76
            else -> false
        }

        return fieldsPresent()
                && 1920 <= byrInt && byrInt <= 2002
                && 2010 <= iyrInt && iyrInt <= 2020
                && 2020 <= eyrInt && eyrInt <= 2030
                && hgtValid
                && hgt?.matches(hgtPattern) ?: false
                && hcl?.matches(hclPattern) ?: false
                && ecl?.matches(eclPattern) ?: false
                && pid?.matches(pidPattern) ?: false
    }
}
