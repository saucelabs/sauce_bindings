class SessionNotStartedException(Exception):
    """
    Thrown when a method is called that requires an active Sauce Session.
    """
    pass

class InvalidPlatformException(Exception):
    """
    Thrown when a method is called that requires a different Sauce Platform.
    """
    pass
