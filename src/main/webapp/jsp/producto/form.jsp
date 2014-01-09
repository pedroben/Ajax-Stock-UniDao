<%-- 
    Document   : form
    Created on : Jan 21, 2013, 10:24:17 AM
    Author     : rafa
--%>
<form class="form-horizontal" action="Controller" method="post" id="productoForm">
    <fieldset>
        <div class="control-group">
            <label class="control-label" for="codigo">C�digo: </label>
            <div class="controls">
                <input id="codigo" name="codigo"
                       type="text" size="30" maxlength="50" autofocus="autofocus" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="descripcion">Descripci�n: </label> 
            <div class="controls">
                <input id="descripcion"
                       name="descripcion" type="text" size="30" maxlength="50" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="precio">Precio: </label> 
            <div class="controls">
                <input id="precio"
                       name="precio" type="text" size="30" maxlength="50" /> 
            </div>
        </div>           
        <div class="control-group">
            <label class="control-label" for="id_tipoproducto">Tipo de producto: </label> 
            <div class="controls">                
                <input readonly="true" id="id_tipoproducto_input" class="input-mini"
                       name="id_tipoproducto" type="text" size="5" maxlength="5"/>  
                <a class="btn btn-mini" id="id_tipoproducto_button" href="#"><i class="icon-search"></i></a>
                <span id="id_tipoproducto_desc" class="alert alert-success"></span>                                       
            </div>
        </div>             
        <div class="control-group">
            <div class="controls">
                <input type="submit" name="enviar"  />
            </div>
        </div>
    </fieldset>
</form>
