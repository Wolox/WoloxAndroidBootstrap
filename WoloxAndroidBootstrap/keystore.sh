#!/bin/sh
cat > app/keystore.gradle << EOF
ext.release_keystore=file('keystore/debug.keystore')
ext.key_alias='androiddebugkey'
ext.key_password='android'
ext.store_password='android'
EOF
