// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
<custom-style>
<style>
        html {
      --lumo-border-radius: calc(var(--lumo-size-m) / 2);

    }

</style>
</custom-style>


`;

document.head.appendChild($_documentContainer.content);
