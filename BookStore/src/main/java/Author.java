public class Author {
    private Long authorId;
    private String firstName;
    private String lastName;
    private String biography;

    public Author(Long authorId, String firstName, String lastName, String biography) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.biography = biography;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getBiography() {
        return biography;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
