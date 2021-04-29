## Testing

All of the files should currently be set up to just run ./rktTests from the current directory.

The Client.jar is in tests, and may need to be swapped out if rebuilt.

The aci in ./rkt called "library-wsserver-latest.aci" will have to be rebuilt if the base image changes. It can be converted from a dockerfile using the docker2aci utility

Very side note: The "sleep Xm" will only work where that version of sleep is supported (swap to seconds for mac)

