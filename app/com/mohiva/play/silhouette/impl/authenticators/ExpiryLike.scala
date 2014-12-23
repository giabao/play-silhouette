package com.mohiva.play.silhouette.impl.authenticators

import com.mohiva.play.silhouette.api.Authenticator
import org.joda.time.DateTime

/**
 * This trait provide an implementation for Authenticator.isValid
 * based on lastUsedDate, expirationDate, idleTimeout
 */
trait ExpiryLike { this: Authenticator =>
  /**
   * @return The last used timestamp.
   */
  def lastUsedDate: DateTime

  /**
   * @return The expiration time.
   */
  def expirationDate: DateTime

  /**
   * @return The time in seconds an authenticator can be idle before it timed out.
   */
  def idleTimeout: Option[Int]

  /**
   * Checks if the authenticator isn't expired and isn't timed out.
   *
   * @return True if the authenticator isn't expired and isn't timed out.
   */
  def isValid = !isExpired && !isTimedOut

  /**
   * Checks if the authenticator is expired. This is an absolute timeout since the creation of
   * the authenticator.
   *
   * @return True if the authenticator is expired, false otherwise.
   */
  private def isExpired = expirationDate.isBeforeNow

  /**
   * Checks if the time elapsed since the last time the authenticator was used is longer than
   * the maximum idle timeout specified in the properties.
   *
   * @return True if sliding window expiration is activated and the authenticator is timed out, false otherwise.
   */
  private def isTimedOut = idleTimeout.isDefined && lastUsedDate.plusMinutes(idleTimeout.get).isBeforeNow
}
