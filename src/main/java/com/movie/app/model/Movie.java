package com.movie.app.model;

public class Movie {

    public String name;
    public String director;
    public long id;
    public String category;

    public Movie(){
    }
    public Movie(long id, String name, String director, String category){
        this.id =id;
        this.name = name;
        this.director = director;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Movie other = (Movie) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString(){
        return "Movie [id = ]" + id + ", name = " + name
                + ", director = " + director + ", category = "
                + category + "]";
    }

}
