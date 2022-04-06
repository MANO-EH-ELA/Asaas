<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <asset:stylesheet src="Index/IndexCss.css" />

    <title>Asaas - Criar conta</title>
    <link rel="shortcut icon" type="image/x-icon" href="/images/favicon.ico" />

  </head>
  <body>
    <main class="container">
      <h2>Registro</h2>
      <form autocomplete="off" id="formid"> 
        <div class="input-field">
          <input type="text" name="nome" id="nome" placeholder="Nome" required/>
          <div class="linha"></div>
        </div>

        <div class="input-field">
          <input type="text" name="cpf" id="cpf" placeholder="CPF" maxlength="14" required />
          <div class="linha"></div>
        </div>

        <div class="input-field">
          <input type="email" name="email" id="email" placeholder="Email" required/>
          <div class="linha"></div>
        </div>

        <div class="input-field">
          <input type="text" name="telefone" id="telefone" placeholder="Telefone" maxlength="15" required/>
          <div class="linha"></div>
        </div>

        <div class="input-field">
          <input type="text" name="cep" id="cep" placeholder="CEP" maxlength="9" required/>
          <div class="linha"></div>
        </div>

        <div class="input-field">
          <input type="text" name="endereco" id="endereco"  placeholder="Endereço" required/>
          <div class="linha"></div>
        </div>

        <div class="input-field">
          <input type="text" name="numero" id="numero" placeholder="Nº" required/>
          <div class="linha"></div>
        </div>

        <div class="input-field">
          <input type="text" name="bairro" id="bairro" placeholder="Bairro" required/>
          <div class="linha"></div>
        </div>

        <div class="input-field">
          <input type="text" name="localidade" id="localidade" placeholder="Localidade" required/>
          <div class="linha"></div>
        </div>

        <div class="input-field">
          <input type="text" name="uf" id="uf" placeholder="UF" required/>
          <div class="linha"></div>
        </div>

      
        <button type="submit" id="send" class="btn" >Registrar</button>
      
      </form>   
    </main>

    <asset:javascript src="Index/IndexController.js" />
    <asset:javascript src="Index/Viacep.js" />
    <asset:javascript src="Index/Mask.js" />

  </body>
</html>
