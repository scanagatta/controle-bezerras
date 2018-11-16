package br.com.controlebezerras.extras;


import java.util.List;

import br.com.controlebezerras.model.Vaca;

/**
 * Created by jonaspfeifer on 07.05.17.
 */
public class SuggestionWrapper {

  List<Vaca> suggestions;

  public List<Vaca> getSuggestions() {
    return suggestions;
  }

  public void setSuggestions(List<Vaca> suggestions) {
    this.suggestions = suggestions;
  }
}
