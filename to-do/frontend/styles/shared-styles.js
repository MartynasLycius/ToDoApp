// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
<custom-style>
  <style>
    html {
      --lumo-font-family: Cambria, "Hoefler Text", Utopia, "Liberation Serif", "Nimbus Roman No9 L Regular", Times, "Times New Roman", serif;
      --lumo-size-xl: 3rem;
      --lumo-size-l: 2.5rem;
      --lumo-size-m: 2rem;
      --lumo-size-s: 1.75rem;
      --lumo-size-xs: 1.5rem;
    }
  </style>
</custom-style>


`;

document.head.appendChild($_documentContainer.content);
