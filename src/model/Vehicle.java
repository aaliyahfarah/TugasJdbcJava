package model;

public class Vehicle {
    private String Id;
    private String AddedAt;
    private Boolean IsAvailable;
    private Integer TypeId;
    private String TypeName;


    public String getId() {
        return this.Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getAddedAt() {
        return this.AddedAt;
    }

    public void setAddedAt(String AddedAt) {
        this.AddedAt = AddedAt;
    }

    public Boolean isIsAvailable() {
        return this.IsAvailable;
    }

    public Boolean getIsAvailable() {
        return this.IsAvailable;
    }

    public void setIsAvailable(Boolean IsAvailable) {
        this.IsAvailable = IsAvailable;
    }

    public Integer getTypeId() {
        return this.TypeId;
    }

    public void setTypeId(Integer TypeId) {
        this.TypeId = TypeId;
    }

    public String getTypeName() {
        return this.TypeName;
    }

    public void setTypeName(String TypeName) {
        this.TypeName = TypeName;
    }
   
}
