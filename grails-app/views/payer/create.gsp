<html>
    <head>
        <meta name="layout" content="main"/>
        <g:set var="entityName" value="${message(code: 'payer.label', default: 'Payer')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
        <asset:stylesheet src="utility/form.css" />
        <asset:javascript src="application.js" />
        <title>Novo Pagador</title>
    </head>
    <body>
      <main class="container">
        <h2 class="h2">Novo Pagador</h2>
        <form data-redirect="${g.createLink([controller: 'payer', action: 'index', params: [id: customerId]])}"
              data-url="${g.createLink([controller: 'payer', action: 'save'])}">

          <div class="form-center">
            <label class="h3"for="name">Nome:</label>
            <input type="text" name="name" id ="name" placeholder="Nome">
          </div>

          <div class="form-center">
            <label class="h3"for="email">E-mail:</label>
            <input type="text" name="email" id="email" placeholder="Email">
          </div>
          
          <div class="two-colums">
            <label class="h3"for="cpfCnpj">CPF/CNPJ:</label>
            <input type="text" name="cpfCnpj" id="cpfCnpj" placeholder="CPF/CNPJ">
          </div>

          <div class="two-colums left">
            <label class="h3"for="phone">Telefone:</label>
            <input type="text" name="phone" id="phone" placeholder="Telefone">
          </div>
          
          <div class="two-colums">
            <label class="h3"for="postalCode">CEP:</label>
            <input type="text" name="postalCode" id="postalCode" placeholder="CEP">
          </div>

          <div class="two-colums left">
            <label class="h3"for="address">Endereço:</label>
            <input type="text" name="address" id="address" placeholder="Endereço">           
          </div>

          <div class="two-colums">
            <label class="h3"for="addressNumber">Nº:</label>
            <input type="text" name="addressNumber" id="addressNumber" placeholder="Nº">
          </div>

          <div class="two-colums left">
            <label class="h3"for="province">Bairro:</label>
            <input type="text" name="province" id="province" placeholder="Bairro">
          </div>

          <div class="two-colums">
            <label class="h3"for="City">Localidade:</label>
            <input type="text" name="city" id="city" placeholder="Localidade">
          </div>

          <div class="two-colums left">
            <label class="h3"for="state">Estado:</label>
            <input type="text" name="state" id="state" placeholder="UF">
          </div>
          
          <input hidden value="${ customerId }" name="customer" id="customer">
          <button class="btn" type="submit">Criar</button>
          <button class="btn" type="reset" onclick="history.go(-1)">Voltar</button>
        </form>   
      </main>
    <asset:javascript src="utility.applications.js" />
    </body>
</html> 
