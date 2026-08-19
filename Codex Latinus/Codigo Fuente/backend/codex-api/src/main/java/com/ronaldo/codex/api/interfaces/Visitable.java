package com.ronaldo.codex.api.interfaces;

import com.ronaldo.codex.api.semantica.Semantica;

/**
 *
 * @author ronaldo
 */
public interface Visitable {

    public void verificarSemantica(Semantica semantica) throws Exception;
}
