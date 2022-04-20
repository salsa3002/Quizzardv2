package lt.vu.mif.quizzardv2.bl.question.json;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mif.quizzardv2.model.question.enums.CategoryEnum;

@Getter
@Setter
public class QuestionSearch {

    Long id;
    String searchText;
    CategoryEnum category;
}
