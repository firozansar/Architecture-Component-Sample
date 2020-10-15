package info.firozansari.architecture_component.Extension

fun String.contains(stringList: List<String>): Boolean {
    for (string in stringList) {
        if ((this.contains(string, true))) {
            return true
        }
    }
    return false
}