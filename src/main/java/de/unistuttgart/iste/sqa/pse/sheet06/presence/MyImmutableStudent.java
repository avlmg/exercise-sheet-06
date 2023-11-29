package de.unistuttgart.iste.sqa.pse.sheet06.presence;

/**
 * Write a description of class ImmutableStudent here. This class must be
 * Immutable
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public final class MyImmutableStudent {
    private final String StudentName;
    private final long MatrikelNummer;
    private final Address Geburtsort;

    public MyImmutableStudent(String studentName, long matrikelNummer, Address geburtsort) {
        StudentName = studentName;
        MatrikelNummer = matrikelNummer;
        Geburtsort = new Address(geburtsort.getStreetname(),geburtsort.getStreetnumber(),); //nicht fertig
    }

    public String getStudentName() {
        return StudentName;
    }

    public long getMatrikelNummer() {
        return MatrikelNummer;
    }
// muss auch geander werden
    public Address getGeburtsort() {
        return Geburtsort;
    }

}
