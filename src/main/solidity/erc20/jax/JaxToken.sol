pragma solidity ^0.4.11;


import "./ERC20.sol";


/**
 * @title JaxToken
 * @dev Very simple ERC20 Token example, where all tokens are pre-assigned to the creator.
 * Note they can later distribute these tokens as they wish using `transfer` and other
 * `StandardToken` functions.
 */
contract JaxToken is ERC20 {

  string public constant name = "JaxToken";
  string public constant symbol = "JAX";
  uint8 public constant decimals = 18;

  uint256 public constant INITIAL_SUPPLY = 10000 * (10 ** uint256(decimals));

  /**
   * @dev Contructor that gives msg.sender all of existing tokens.
   */
  constructor () public {
    _mint(msg.sender, INITIAL_SUPPLY);
  }

}
