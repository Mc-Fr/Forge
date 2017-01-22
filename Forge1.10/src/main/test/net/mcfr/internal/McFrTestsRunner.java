/*
 * This file is part of Michael Vorburger's SwissKnightMinecraft project, licensed under the MIT
 * License (MIT).
 *
 * Copyright (c) Michael Vorburger <http://www.vorburger.ch> Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package net.mcfr.internal;

import java.lang.reflect.Field;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.TestClass;

/**
 * Lanceur de tests JUnit permettant d'exécuter les tests dans le ClassLoader du serveur Minecraft.
 *
 * @author Michael Vorburger
 * @author Mc-Fr
 */
public class McFrTestsRunner extends BlockJUnit4ClassRunner {
  private static MinecraftServerStarter starter = MinecraftServerStarter.getInstance();
  
  /**
   * @param testClass la classe de test
   */
  public McFrTestsRunner(Class<?> testClass) throws InitializationError {
    super(startServer(testClass));
  }
  
  /**
   * Cette méthode permet de démarrer le serveur avant l'invocation du constructeur parent et le
   * lancement des tests.
   *
   * @return la classe de test
   */
  private static Class<?> startServer(Class<?> testClass) throws InitializationError {
    try {
      if (!starter.isRunning()) {
        starter.startServer();
        starter.waitForServerStartupCompletion();
      }
    }
    catch (Throwable e) {
      throw new InitializationError(e);
    }
    
    return testClass;
  }
  
  /**
   * Crée la classe de test à travers le ClassLoader du serveur Minecraft.
   */
  @Override
  protected TestClass createTestClass(Class<?> testClass) {
    try {
      return super.createTestClass(starter.getMinecraftServerClassLoader().loadClass(testClass.getName()));
    }
    catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  
  /**
   * Crée le test et instancie le champ <i>game</i> de la classe de test avec l'instance du serveur.
   */
  @Override
  protected Object createTest() throws Exception {
    Object testClass = super.createTest();
    Field gameField = testClass.getClass().getField("game");
    
    gameField.setAccessible(true);
    gameField.set(null, starter.getGame());
    
    return testClass;
  }
}