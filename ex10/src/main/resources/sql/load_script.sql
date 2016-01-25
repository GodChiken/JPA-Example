DROP PROCEDURE IF EXISTS proc_multiply;
DELIMITER $$
CREATE PROCEDURE proc_multiply(INOUT inParam INT, INOUT outParam INT)
  BEGIN
    SET outParam = inParam * 2;
  END $$
DELIMITER ;